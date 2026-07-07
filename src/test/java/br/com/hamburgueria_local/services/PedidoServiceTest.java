package br.com.hamburgueria_local.services;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoInteractions;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import br.com.hamburgueria_local.dto.request.ItemPedidoRequest;
import br.com.hamburgueria_local.dto.request.PedidoRequest;
import br.com.hamburgueria_local.dto.response.PedidoResponse;
import br.com.hamburgueria_local.entities.Pedido;
import br.com.hamburgueria_local.entities.Produto;
import br.com.hamburgueria_local.entities.Usuario;
import br.com.hamburgueria_local.enums.PerfilUsuario;
import br.com.hamburgueria_local.enums.StatusPedido;
import br.com.hamburgueria_local.enums.TipoPedido;
import br.com.hamburgueria_local.exceptions.PedidoCanceladoException;
import br.com.hamburgueria_local.exceptions.PedidoSemItensException;
import br.com.hamburgueria_local.exceptions.RecursoNaoEncontradoException;
import br.com.hamburgueria_local.repositories.PagamentoRepository;
import br.com.hamburgueria_local.repositories.PedidoRepository;
import br.com.hamburgueria_local.repositories.ProdutoRepository;
import br.com.hamburgueria_local.repositories.UsuarioRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class PedidoServiceTest {

    @Mock private PedidoRepository pedidoRepository;
    @Mock private ProdutoRepository produtoRepository;
    @Mock private UsuarioRepository usuarioRepository;
    @Mock private PagamentoRepository pagamentoRepository;

    @InjectMocks
    private PedidoService pedidoService;

    private Usuario usuario;
    private Produto produto;
    private PedidoRequest request;

    @BeforeEach
    void setUp() {
        usuario = new Usuario();
        usuario.setId(1L);
        usuario.setNome("Usuario Teste");
        usuario.setPerfil(PerfilUsuario.CLIENTE);

        produto = new Produto();
        produto.setId(1L);
        produto.setNome("X-Burguer");
        produto.setPreco(new BigDecimal("20.00"));

        ItemPedidoRequest itemRequest = new ItemPedidoRequest();
        itemRequest.setProdutoId(1L);
        itemRequest.setQuantidade(2);

        request = new PedidoRequest();
        request.setUsuarioId(1L);
        request.setTipoPedido(TipoPedido.BALCAO);
        request.setItens(List.of(itemRequest));
    }

    @Test
    void criar_deveCriarPedidoComSucesso() {
        when(usuarioRepository.findById(1L)).thenReturn(Optional.of(usuario));
        when(produtoRepository.findById(1L)).thenReturn(Optional.of(produto));
        when(pedidoRepository.save(any(Pedido.class))).thenAnswer(invocation -> {
            Pedido p = invocation.getArgument(0);
            p.setId(1L);
            return p;
        });

        PedidoResponse response = pedidoService.criar(request);

        assertThat(response).isNotNull();
        assertThat(response.getId()).isEqualTo(1L);
        assertThat(response.getUsuarioId()).isEqualTo(1L);
        assertThat(response.getUsuarioNome()).isEqualTo("Usuario Teste");
        assertThat(response.getStatus()).isEqualTo(StatusPedido.RECEBIDO);
        assertThat(response.getSubtotal()).isEqualByComparingTo("40.00");
        assertThat(response.getValorTotal()).isEqualByComparingTo("40.00");
        assertThat(response.getDesconto()).isEqualByComparingTo(BigDecimal.ZERO);
        assertThat(response.getItens()).hasSize(1);
        assertThat(response.getItens().get(0).getSubtotalItem()).isEqualByComparingTo("40.00");

        verify(pedidoRepository).save(any(Pedido.class));
    }

    @Test
    void criar_deveCalcularSubtotalComMultiplosItens() {
        Produto produto2 = new Produto();
        produto2.setId(2L);
        produto2.setNome("Batata Frita");
        produto2.setPreco(new BigDecimal("10.00"));

        ItemPedidoRequest item2 = new ItemPedidoRequest();
        item2.setProdutoId(2L);
        item2.setQuantidade(3);

        request.setItens(Arrays.asList(request.getItens().get(0), item2));

        when(usuarioRepository.findById(1L)).thenReturn(Optional.of(usuario));
        when(produtoRepository.findById(1L)).thenReturn(Optional.of(produto));
        when(produtoRepository.findById(2L)).thenReturn(Optional.of(produto2));
        when(pedidoRepository.save(any(Pedido.class))).thenAnswer(invocation -> invocation.getArgument(0));

        PedidoResponse response = pedidoService.criar(request);

        assertThat(response.getSubtotal()).isEqualByComparingTo("70.00");
        assertThat(response.getItens()).hasSize(2);
    }

    @Test
    void criar_deveLancarExcecaoQuandoItensForemNulos() {
        request.setItens(null);

        assertThrows(PedidoSemItensException.class, () -> pedidoService.criar(request));
        verifyNoInteractions(usuarioRepository, produtoRepository, pedidoRepository);
    }

    @Test
    void criar_deveLancarExcecaoQuandoItensForemVazios() {
        request.setItens(List.of());

        assertThrows(PedidoSemItensException.class, () -> pedidoService.criar(request));
    }

    @Test
    void criar_deveLancarExcecaoQuandoUsuarioNaoEncontrado() {
        when(usuarioRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(RecursoNaoEncontradoException.class, () -> pedidoService.criar(request));
        verifyNoInteractions(produtoRepository, pedidoRepository);
    }

    @Test
    void criar_deveLancarExcecaoQuandoProdutoNaoEncontrado() {
        when(usuarioRepository.findById(1L)).thenReturn(Optional.of(usuario));
        when(produtoRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(RecursoNaoEncontradoException.class, () -> pedidoService.criar(request));
        verifyNoInteractions(pedidoRepository);
    }

    @Test
    void atualizarStatus_deveAtualizarComSucesso() {
        Pedido pedidoExistente = new Pedido();
        pedidoExistente.setId(1L);
        pedidoExistente.setUsuario(usuario);
        pedidoExistente.setStatus(StatusPedido.RECEBIDO);
        pedidoExistente.setItens(List.of());
        pedidoExistente.setSubtotal(BigDecimal.ZERO);
        pedidoExistente.setDesconto(BigDecimal.ZERO);
        pedidoExistente.setTotal(BigDecimal.ZERO);

        when(pedidoRepository.findById(1L)).thenReturn(Optional.of(pedidoExistente));
        when(pedidoRepository.save(any(Pedido.class))).thenAnswer(invocation -> invocation.getArgument(0));

        PedidoResponse response = pedidoService.atualizarStatus(1L, StatusPedido.EM_PREPARO);

        assertThat(response.getStatus()).isEqualTo(StatusPedido.EM_PREPARO);
        verify(pedidoRepository).save(pedidoExistente);
    }

    @Test
    void atualizarStatus_deveLancarExcecaoQuandoPedidoNaoEncontrado() {
        when(pedidoRepository.findById(99L)).thenReturn(Optional.empty());

        assertThrows(RecursoNaoEncontradoException.class,
                () -> pedidoService.atualizarStatus(99L, StatusPedido.EM_PREPARO));
    }

    @Test
    void atualizarStatus_deveLancarExcecaoQuandoPedidoJaCancelado() {
        Pedido pedidoCancelado = new Pedido();
        pedidoCancelado.setId(1L);
        pedidoCancelado.setUsuario(usuario);
        pedidoCancelado.setStatus(StatusPedido.CANCELADO);

        when(pedidoRepository.findById(1L)).thenReturn(Optional.of(pedidoCancelado));

        assertThrows(PedidoCanceladoException.class,
                () -> pedidoService.atualizarStatus(1L, StatusPedido.ENTREGUE));

        verify(pedidoRepository, never()).save(any());
    }
}

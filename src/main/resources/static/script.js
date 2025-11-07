const API_URL = 'http://localhost:8080/produtos'
let produtoEmEdicao = null;

document.addEventListener('DOMContentLoaded', function() {
    carregarProdutos();
});

async function handleResponse(response) {
    if(response.status === 204){
        return null;
    }

    const data = await response.json();

    console.log('Resposta do backend:', data);

    if(!response.ok){
        const errorMessage = data.mensagem || data.erro || 'Erro na operacao';
        throw new Error(errorMessage);
    }

    return data;
}

function carregarProdutos(){
    fetch(API_URL)
    .then(handleResponse)
    .then(produtos => {
        exibirProdutos(produtos);
    })
    .catch(error => {
        console.log("Erro: " + error);
        alert('Erro ao carregar produtos: ' + error.message);
    });
}

function exibirProdutos(produtos){
    const tbody = document.querySelector('tbody');

    tbody.innerHTML = '';

    if(produtos.length === 0) {
        tbody.innerHTML = '<tr><td colspan="6">Nenhum produto encontrado</td></tr>';
        return;
    }

    produtos.forEach(produto => {
        const tr = document.createElement('tr');

        tr.innerHTML = `
        <td>${produto.id}</td>
        <td>${produto.nome}</td>
        <td>${produto.descricao || 'Sem Descrição'}</td>
        <td>${produto.preco.toFixed(2)}</td>
        <td>${produto.quantidade}</td>
        <td>
        <button onclick="abrirFormularioEdicao('${produto.id}')">Editar</button>
        <button onclick="deletarProduto('${produto.id}')">Excluir</button>
        </td>
        `;

        tbody.appendChild(tr);
    });
}

function buscarPorNome() {
    const nome = document.getElementById('campoBuscaNome').value.trim();
    
    if (nome === '') {
        alert('Digite um nome para buscar');
        return;
    }
    
    fetch(`${API_URL}/buscar?nome=${encodeURIComponent(nome)}`)
    .then(handleResponse)
    .then(produtos => {
        exibirProdutos(produtos);
        
        if (produtos.length === 0) {
            alert('Nenhum produto encontrado com esse nome');
        }
    })
    .catch(error => {
        console.error('Erro:', error);
        alert('Erro ao buscar produtos: ' + error.message);
    });
}

function buscarPorId() {
    const id = document.getElementById('campoBuscaId').value.trim();
    
    if (id === '') {
        alert('Digite um id para buscar');
        return;
    }
    
    fetch(`${API_URL}/${id}`)
    .then(handleResponse)
    .then(produto => {
        exibirProdutos([produto]);
    })
    .catch(error => {
        console.error('Erro:', error);
        alert('Produto não encontrado com esse ID');
    });
}

function limparBusca() {
    document.getElementById('campoBuscaNome').value = '';
    document.getElementById('campoBuscaId').value = '';
    carregarProdutos();
}

function mostrarFormulario(){
    document.getElementById('formularioProduto').style.display = 'block';
}

function cancelar(){
    document.getElementById('formularioProduto').style.display = 'none';
    document.getElementById('nomeProduto').value = '';
    document.getElementById('descricaoProduto').value = '';
    document.getElementById('precoProduto').value = '';
    document.getElementById('quantidadeProduto').value = '';
    produtoEmEdicao = null;
}

function abrirFormularioEdicao(id) {
    fetch(`${API_URL}/${id}`)
        .then(handleResponse)
        .then(produto => {
            produtoEmEdicao = produto.id;
            document.getElementById('formularioProduto').style.display = 'block';
            
            document.getElementById('nomeProduto').value = produto.nome;
            document.getElementById('descricaoProduto').value = produto.descricao || '';
            document.getElementById('precoProduto').value = produto.preco;
            document.getElementById('quantidadeProduto').value = produto.quantidade;
        })
        .catch(error => {
            console.error('Erro:', error);
            alert('Erro ao carregar produto para edição');
        });
}


function salvarProduto() {
    const nome = document.getElementById('nomeProduto').value;
    const descricao = document.getElementById('descricaoProduto').value;
    const preco = parseFloat(document.getElementById('precoProduto').value);
    const quantidade = parseInt(document.getElementById('quantidadeProduto').value);

    const produto = { nome, descricao, preco, quantidade };

    if (produtoEmEdicao) {
        editarProduto(produtoEmEdicao, produto)
        produtoEmEdicao = null;
    } else {
        criarProduto(produto)
    }
    cancelar();
}

function criarProduto(produto) {
    fetch(API_URL, {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(produto)
    })
    .then(handleResponse)
    .then(() => {
        alert('✅ Produto criado!');
        carregarProdutos();
    })
    .catch(error => {
        console.error('Erro:', error);
        alert('❌ ' + error.message);
    });
}

function editarProduto(id, produto) {
    fetch(`${API_URL}/${id}`, {
        method: 'PUT',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(produto)
    })
    .then(handleResponse)
    .then(() => {
        alert('✅ Produto atualizado!');
        carregarProdutos();
    })
    .catch(error => {
        console.error('Erro:', error);
        alert('❌ ' + error.message);
    });
}

function deletarProduto(id) {
    if (!confirm('Tem certeza que deseja deletar este produto?')) {
        return;
    }
    
    fetch(`${API_URL}/${id}`, {
        method: 'DELETE'
    })
    .then(handleResponse)
    .then(() => {
        alert('✅ Produto deletado com sucesso!');
        carregarProdutos();
    })
    .catch(error => {
        console.error('Erro:', error);
        alert('❌ ' + error.message);
    });
}
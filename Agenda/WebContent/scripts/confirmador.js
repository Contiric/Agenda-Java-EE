/**
 * Confimação de exclusão de contato
 */



function confirmar(idcontato) {
	let resposta = confirm("Confirma a exclusão deste contato?")
	if (resposta === true) {
		window.location.href = "delete?idcontato=" + idcontato
	}
}
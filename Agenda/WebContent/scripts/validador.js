/**
 * 
 */

function validar() {
	let nome = cadastroContato.nome.value
	let fone = cadastroContato.fone.value
	let email = cadastroContato.email.value
	
	if (nome == "") {
		alert("Por favor, preencha o campo nome")
		cadastroContato.nome.focus()
		return false
	} else if (fone == "") {
		alert("Por favor, preencha o campo nome")
		cadastroContato.fone.focus()
		return false
	} else {
		document.forms["cadastroContato"].submit()
	}
}

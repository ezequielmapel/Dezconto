$('#cupomPromo').keyup(function(){
	var val= $('#cupomPromo').val();
	var param = {idCupom:val};
	$.get('/lojista/calculadora/cupom-promocional', param, function(data){
		$('#cupomPromo').css('border', data);	
	});
});

$('#emailCli').keyup(function(){
	var param = {emailUser: $('#emailCli').val(), idCupom: $('#cupomPromo').val()};
	$.get('/lojista/calculadora/verificar-usuario', param, function(data){
		$('#emailCli').css('border', data);
	});
});


$('#calcDesc').click(function(){
	var idCupom = $('#cupomPromo').val();
	var email = $('#emailCli').val();
	var valCompra = $('#valCompra').val();	

	var param = {idCupom: idCupom, email: email, valCompra: valCompra};
	if(idCupom != "" && email != ""){
		$.get('/lojista/calculadora/calcdezconto', param, function(data){
			$('#valDesc').html(data);
		});	
	}
});

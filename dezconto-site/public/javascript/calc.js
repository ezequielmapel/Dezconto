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


/*
$('#btnVal').on('click',function(){
    var parm = {idCupom: $('#cupomPromo').val(), emailUser: $('#emailCliente').val()};
    $.get('/lojista/calculadora', parm, function(data){
       
    });
});

*/

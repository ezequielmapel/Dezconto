$('#btnVal').on('click',function(){
    console.log('click');
    var parm = {idCupom: $('#cupomPromo').val(), emailUser: $('#emailCli').val()};
    $.get('/lojista/calculadora', parm, function(data){
       
    });
});
// function calc(){
    
//     var config = {
//         apiKey: "AIzaSyARg7yfTwdZXi6sdZIY78X-5Y9gS-d0wFs",
//         authDomain: "dezconto-44c81.firebaseapp.com",
//         databaseURL:"https://dezconto-44c81.firebaseio.com",
//         projectId: "dezconto-44c81",
//         storageBucket: "dezconto-44c81.appspot.com",
//         messagingSenderId: "568238419017"
//     };
    

//     firebase.initializeApp(config);
//     var database = firebase.database();
//     console.log(firebase.auth().currentUser.uid());

//     var idC = $('cupomPromo').val();
//     var rCupons = database.ref('lojista_cupom').child(firebase.auth().currentUser.uid).orderBy('idCupom').equalTo(idC);
// 		rCupons.once('value', function(snapshot){
//             treeCupom = snapshot.val()
//             console.log(treeCupom);
// 		});
    
//     };


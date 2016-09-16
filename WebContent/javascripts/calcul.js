var somme = function(){

}

$(function(){
    var total = 0.0;

    $('.prixElement').each(function(){
        var prixProduit = parseFloat($(this).text().replace(',','.'));
        console.log(prixProduit);
        total += prixProduit;
        });
    $('#total').text(total.toString().replace('.',','));
});
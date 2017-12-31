
$(function(){
    var tbody = $('#corpo-monitoramento');
    
    var filtro_select;
    $( "#select-filtro" ).change(function() {
        $( "#select-filtro option:selected" ).each(function() {
            filtro_select = $( this ).val();
        });
        //alert(filtro_select);
        $( "#label-input" ).text( filtro_select );
        if(filtro_select == "chassi"){
            $("#modelo_chassi").text( "Modelo" );
        }
        if(filtro_select == "modelo"){
            $("#modelo_chassi").text( "Chassi" );
        }
    }).trigger( "change" );
    
    $('#btn-monitorar').click(function(event){
        event.preventDefault();
    
        var input_filtro = $('#input-filtro').val();

        //$('#corpo-monitoramento').children().remove();
        $('#corpo-monitoramento tr').remove();
        $.get('http://18.231.62.135:5000/collection',function(dataReceived){
            
            $(dataReceived).each(function(){

                if(filtro_select == 'chassi' && input_filtro == this.chassi){
                    tbody.append(criaTr(this.data, this.hora, this.rpm, this.velocidade, this.tipo_combustivel, this.pressao_combustivel, this.modelo));
                }
                if(filtro_select == 'modelo' && input_filtro == this.modelo){
                    tbody.append(criaTr(this.data, this.hora, this.rpm, this.velocidade, this.tipo_combustivel, this.pressao_combustivel, this.chassi));
                }
            });
        });


    });
});

function criaTr(data, hora, rpm, velocidade, fuel_type, fuel_pressure, modelo_chassi) {
    var tr = $('<tr>');
    tr.append($('<td>').text(data));
    tr.append($('<td>').text(hora));
    tr.append($('<td>').text(rpm));
    tr.append($('<td>').text(velocidade));
    tr.append($('<td>').text(fuel_type));
    tr.append($('<td>').text(fuel_pressure));
    tr.append($('<td>').text(modelo_chassi));
    return tr;
}


var express = require('express'); //importa o módulo express
var app = express(); //variável app é uma instância do express
//////////////////////////////////////////////////////
//// configuração de permissões - cors
var allowCors = function(req, res, next) {

	res.header('Access-Control-Allow-Origin', '*'); //habilita requisições de um determinado endereço
    res.header('Access-Control-Allow-Methods', 'GET,PUT,POST,DELETE'); 
    res.header('Access-Control-Allow-Headers', 'Content-Type');
    res.header('Access-Control-Allow-Credentials', 'true');

	next();
}

//////////////////////////////////


app.listen(5000); //o app ficará monitorando a porta 5000
app.use(allowCors);
///////////////////////////////////////////////////////////////
///configuração do formato de dados
var jsonParse = require('body-parser'); //importa o modulo body-parser - Responsável pelas conversões json
app.use(jsonParse.json()); //configura a app para utilizar o jsonParse
app.use(jsonParse.urlencoded({
    extended: true
})); //configura o encoder
////////////////////////////////////////////////////////////////
///configuração banco dados
var url_banco = 'mongodb://18.231.62.135:27017/car_monitor';
var mongoose = require('mongoose').connect(url_banco);
var database = mongoose.connection;

var collection;
database.on('error', console.error.bind(console, 'Erro ao conectar no banco')); //verifica se houve erro
database.once('open', function () { // executa a função anônima ao abrir a conexão com o banco
    var jsonFormat = mongoose.Schema({ //define o formato do documento json
    //var jsonFormat = mongoose.Schema(); //define o formato do documento json
        rpm: String,
        tipo_combustivel: String,
        pressao_motor: String
    });
    collection = mongoose.model('reading_sensors', jsonFormat); //obtém a coleção do banco
    //collection = mongoose.model('reading_sensors'); //obtém a coleção do banco
});
/////////////////////////////////////////////////////////////////

app.get('/', function (request, response) { //quando feita uma requisição get, este método é chamado
    response.end('Servidor on');
});

app.get('/collection', function (request, response) { //quando feita uma requisição get para o '/collection', este método é chamado
    //response.end(Json);
    collection.find({}, function (error, data) { //retorna os documentos json da coleção
        if(error){
            response.json({error: 'Não foi possível retornar os dados'});
        }else{
            response.json(data);
        }
    });
});

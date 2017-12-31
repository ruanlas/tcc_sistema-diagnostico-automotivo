Orientações para a instalação do Web Service
===============================
####
OBS: criar uma pasta e mover os arquivos para dentro
###

#1) instalar o nodejs

    --OBS: linux:
        -> curl -sL https://deb.nodesource.com/setup_6.x | sudo -E bash -
        -> sudo apt-get install -y nodejs

#2) é necessário instalar o 'nodemom' via terminal.

    -> npm install -g nodemom

#3) é necessário criar o package

    -> npm init

#4) instalação das dependências

    -> npm install express --save
    -> npm install body-parser --save
    -> npm install validator --save
    -> npm install mongoose --save

#5) rodar o servidor

    -> node app.js

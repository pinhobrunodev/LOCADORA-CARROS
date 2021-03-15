<%@ page language="java" contentType="text/html; utf-8"
	pageEncoding="utf-8"%>
   
  
  
  
  
<!DOCTYPE html>
<html lang="pt-br">
    <head>
        <meta charset="utf-8">
        <link rel="stylesheet" href="editarVeiculo.css">
        <title>Registro Veiculo</title>
    </head>
<body>
    
<div id = "container">
   
    <h1>CADASTRAR</h1>

    <form action="atualizarVeiculo" method="POST" name="atualizandoVeiculo">
    	<h2>CODIGO</h2>
        <input type="text" value="<%out.print(request.getAttribute("id"));%>" required readonly="readonly" name="id">  <br>
        <h2>MODELO</h2>
        <input type="text" value="<%out.print(request.getAttribute("modelo"));%>" required readonly="readonly" name="modelo">  <br>
        <h2>PLACA</h2>
        <input type="text" value="<%out.print(request.getAttribute("placa"));%>" required readonly="readonly"  name="placa">  <br>
       <h2>COR</h2>
        <input type="text" value="<%out.print(request.getAttribute("cor"));%>" required readonly="readonly"  name="cor">  <br>
       <h2>VALOR</h2>
       <input type="text" required name="valor" value="<%out.print(request.getAttribute("valor"));%>"> <br>
       
       
        <input class="botao" type="submit" value="Editar">
    </form>





</div>



</body>
</html>
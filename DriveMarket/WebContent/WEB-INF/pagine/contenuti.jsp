<%@ page import="java.util.ArrayList" %>
<%@ page import="model.product.Product" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%ArrayList<Product> products=(ArrayList<Product>) application.getAttribute("products"); %>

<div class="container">
		<div class="content">
			<% for(Product p : products){ %>
				<div class="producto">
					<a href="producto.php?producto={{producto.id_prod}}">
						<img src=<%= p.getMain_photo() %>>
						<h3><%= p.getName() %></h3>
					</a>
				</div>
			<% } %> 
		</div>

	<aside>
		<h2>Opciones de Usuario</h2>
		<ul>
			{% if not user %}
				<li>
					<a href="login.php">Login</a>
				</li>
			{% else %}
				<li>Usuario: {{ user.nick }}</li>
				<li>
					<a href="modificar_usuario.php">Mi Cuenta</a>
				</li>
				<li>
					<a href="logout.php">Logout</a>
				</li>

				{% if user.tipo_usuario == 'superusuario' %}
					<li>
						<a href="cambiar_tipo_usuario.php">Cambiar tipo de Usuario</a>
					</li>
				{% endif %}
				{% if user.tipo_usuario == 'superusuario' or user.tipo_usuario == 'moderador' %}
					<li>
						<a href="lista_comentarios.php">Lista de Comentarios</a>
					</li>
				{% endif %}
				{% if user.tipo_usuario == 'superusuario' or user.tipo_usuario == 'gestor' %}
					<li>
						<a href="lista_productos.php">Lista Productos</a>
					</li>
					<li>
						<a href="insertar_producto.php">Añadir Producto</a>
					</li>
				{% endif %}



			{% endif %}
			
		</ul>
		<br><br>
		<h2>Descripción</h2>
		
		<ul>
			<li>
				<a href="index.php">Nuestra historia</a>
			</li>
			<li>
				<a href="index.php">Modelos Antiguos</a>
			</li>
          	</ul>

		<br>
		<p>Página dedicada a ofrecer información sobre los diversos productos que ofrece la marca Tesla.</p>
	</aside>

</div>
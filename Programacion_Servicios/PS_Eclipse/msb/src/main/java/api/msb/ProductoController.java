package api.msb;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import api.models.Producto;

@RestController
@RequestMapping("/api/productos") // URL Base
public class ProductoController {

	@Autowired
	private MongoTemplate mongoTemplate;

	//GET: Listar todos los elementos
	@GetMapping
	public List<Producto> listarTodos() {
		return mongoTemplate.findAll(Producto.class);
	}

	//GET: Obtener uno por ID
	@GetMapping("/{id}")
	public ResponseEntity<Producto> obtenerPorId(@PathVariable String id) {
		Producto producto = mongoTemplate.findById(id, Producto.class);
		if (producto != null) {
			return ResponseEntity.ok(producto);
		} else {
			return ResponseEntity.notFound().build();
		}
	}

	//POST: Crear un nuevo registro
	@PostMapping
	public Producto crear(@RequestBody Producto producto) {
		// Al guardar sin ID, Mongo genera uno nuevo
		return mongoTemplate.save(producto);
	}

	//PUT: Actualizar
	@PutMapping("/{id}")
	public ResponseEntity<Producto> actualizar(@PathVariable String id, @RequestBody Producto producto) {
		Producto existe = mongoTemplate.findById(id, Producto.class);
		if (existe != null) {
			producto.setId(id);
			return ResponseEntity.ok(mongoTemplate.save(producto));
		}
		return ResponseEntity.notFound().build();
	}

	//DELETE: Eliminar un registro
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> eliminar(@PathVariable String id) {
		Query query = new Query();
		query.addCriteria(Criteria.where("id").is(id));

		// deleteFirst borra el documento que coincida
		var resultado = mongoTemplate.remove(query, Producto.class);

		if (resultado.getDeletedCount() > 0) {
			return ResponseEntity.noContent().build(); // 204 No Content
		} else {
			return ResponseEntity.notFound().build(); // 404 Not Found
		}
	}
}
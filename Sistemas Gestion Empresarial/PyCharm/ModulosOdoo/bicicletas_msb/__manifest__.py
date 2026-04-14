{
    "name": "Gestión de alquiler de bicicletas",
    "description": "Módulo personalizado para la gestión de clientes, bicicletas y alquileres de la empresa PedaOlé.",
    "author": "Mario Sanchez",
    "version": "0.1",
    "depends": ["base"],
    "application": True,
    "category": "Sales",
    "data": [
        "security/ir.model.access.csv",
        "views/cliente_view.xml",
        "views/bicicleta_view.xml",
        "views/alquiler_view.xml",
        "views/analisis_view.xml",
        "views/menu.xml",
    ]
}
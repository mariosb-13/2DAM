{
    "name": "Gestión reservas",
    "description": "Nuevo módulo para gestionar las reservas de actividades",
    "author" :  "Mario Sanchez",
    "version" : "0.1",
    "depends" : ["base"],
    "application": True,
    "category": "Productivity",
    "data": [
        "security/ir.model.access.csv",
        "views/reserva_view.xml",
        "views/turista_view.xml",
        "views/actividad_view.xml",
        "views/analisis_view.xml",
        "views/categoria_actividad_view.xml",
        "views/guia_turistico_view.xml",
        "views/comercial_view.xml",
        "views/menu.xml",
    ]
}
{
    'name': 'Gestión de Reservas de Actividades',
    'version': '1.0',
    'category': 'Sales',
    'author': "Mario Sánchez",
    'summary': 'Sistema de reservas de actividades',
    'description': """
        Módulo para gestión de reservas de actividades, turistas y análisis de ventas.
    """,
    'depends': ['base'],
    'data': [
        'security/ir.model.access.csv',
        'views/reserva_view.xml',
        'views/actividad_view.xml',
        'views/turista_view.xml',
        'views/categoria_view.xml',
        'views/guia_view.xml',
    ],
    'installable': True,
    'application': True,
}
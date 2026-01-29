{
    'name': 'Gestión de Reservas de Actividades',
    'version': '1.0',
    'category': 'Sales',
    'summary': 'Sistema de reservas de actividades',
    'description': """
        Módulo para gestión de reservas de actividades, turistas y análisis de ventas.
    """,
    'depends': ['base'],
    'data': [
        'security/ir.model.access.csv',
        'views/views.xml',
    ],
    'installable': True,
    'application': True,
}
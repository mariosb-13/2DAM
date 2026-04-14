from odoo import models, fields, api


class Bicicleta(models.Model):
    _name = "bicicletas_msb.bicicleta"
    _rec_name = "nombre_representativo"

    nombre_representativo = fields.Char(compute="_nombre")

    codigo = fields.Char(required=True)
    tipo = fields.Selection([
        ('Bicicleta', 'Bicicleta'),
        ('Bicicleta eléctrica', 'Bicicleta eléctrica'),
        ('Patinete', 'Patinete')
    ], required=True)
    precio_dia = fields.Float(required=True)
    comentarios = fields.Text()

    alquileres = fields.One2many("bicicletas_msb.alquiler", "bicicleta", string="Alquileres")

    numero_reservas = fields.Integer(compute="_compute_analisis_bici")
    total_dias_reservados = fields.Integer(compute="_compute_analisis_bici")
    total_ingresado = fields.Float(compute="_compute_analisis_bici")


    @api.depends("codigo", "tipo", "precio_dia")
    def _nombre(self):
        for bici in self:
            if bici.codigo and bici.tipo and bici.precio_dia:
                bici.nombre_representativo = f"{bici.codigo} - {bici.tipo} ({bici.precio_dia}€/día)"
            else:
                bici.nombre_representativo = "Nueva Bicicleta"  # o pon = False si prefieres que no salga nada

    # Cálculo para el Top Bicicletas (solo alquileres activos)
    @api.depends("alquileres.estado", "alquileres.precio", "alquileres.fecha_inicio", "alquileres.fecha_fin")
    def _compute_analisis_bici(self):
        for bici in self:
            alquileres_activos = bici.alquileres.filtered(lambda a: a.estado == 'Activa')
            bici.numero_reservas = len(alquileres_activos)
            bici.total_ingresado = sum(alquileres_activos.mapped('precio'))

            dias = 0
            for alq in alquileres_activos:
                if alq.fecha_inicio and alq.fecha_fin:
                    dias += (alq.fecha_fin - alq.fecha_inicio).days
            bici.total_dias_reservados = dias

    @api.constrains("codigo")
    def _check_codigo_unico(self):
        for bici in self:
            if bici.codigo:
                duplicados = self.env['bicicletas_msb.bicicleta'].search([
                    ('codigo', '=', bici.codigo),
                    ('id', '!=', bici.id)
                ])
                if duplicados:
                    raise ValidationError("El código de la bicicleta debe ser único.")
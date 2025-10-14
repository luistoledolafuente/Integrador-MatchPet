from django.db import models
from django.contrib.auth.models import AbstractUser

# Tabla: Especies

class Especie(models.Model):
    nombre_especie = models.CharField(max_length=50, unique=True)

    def __str__(self):
        return self.nombre_especie


# Tabla: Temperamentos
class Temperamento(models.Model):
    nombre_temperamento = models.CharField(max_length=100, unique=True)

    def __str__(self):
        return self.nombre_temperamento


# Tabla: Refugios
class Refugio(models.Model):
    nombre = models.CharField(max_length=255)
    direccion = models.TextField()
    ciudad = models.CharField(max_length=100)
    telefono = models.CharField(max_length=20, blank=True)
    email = models.EmailField(unique=True)
    persona_contacto = models.CharField(max_length=255, blank=True)
    fecha_registro = models.DateTimeField(auto_now_add=True)

    def __str__(self):
        return self.nombre


# Tabla: Adoptantes
class Adoptante(models.Model):
    nombre_completo = models.CharField(max_length=255)
    email = models.EmailField(unique=True)
    telefono = models.CharField(max_length=20, blank=True)
    fecha_nacimiento = models.DateField(null=True, blank=True)
    direccion = models.TextField(blank=True)
    ciudad = models.CharField(max_length=100, blank=True)
    hash_contraseña = models.TextField()
    fecha_creacion_perfil = models.DateTimeField(auto_now_add=True)

    def __str__(self):
        return self.nombre_completo


# Tabla: Razas
class Raza(models.Model):
    nombre_raza = models.CharField(max_length=100, unique=True)
    especie = models.ForeignKey(Especie, on_delete=models.CASCADE)

    def __str__(self):
        return self.nombre_raza


# Tabla: Animales
class Animal(models.Model):
    GENERO_CHOICES = [('Macho', 'Macho'), ('Hembra', 'Hembra')]
    TAMANO_CHOICES = [('Pequeño', 'Pequeño'), ('Mediano', 'Mediano'), ('Grande', 'Grande')]
    ENERGIA_CHOICES = [('Bajo', 'Bajo'), ('Medio', 'Medio'), ('Alto', 'Alto')]
    ESTADO_CHOICES = [('Disponible', 'Disponible'), ('En proceso', 'En proceso'), ('Adoptado', 'Adoptado')]

    nombre = models.CharField(max_length=100)
    especie = models.ForeignKey(Especie, on_delete=models.CASCADE)
    raza = models.ForeignKey(Raza, on_delete=models.CASCADE)
    refugio = models.ForeignKey(Refugio, on_delete=models.CASCADE)
    fecha_nacimiento_aprox = models.DateField(null=True, blank=True)
    genero = models.CharField(max_length=10, choices=GENERO_CHOICES)
    tamaño = models.CharField(max_length=10, choices=TAMANO_CHOICES, blank=True)
    descripcion_personalidad = models.TextField(blank=True)
    nivel_energia = models.CharField(max_length=10, choices=ENERGIA_CHOICES, blank=True)
    compatible_niños = models.BooleanField(default=False)
    compatible_otras_mascotas = models.BooleanField(default=False)
    foto_principal_url = models.TextField(blank=True)
    estado_adopcion = models.CharField(max_length=20, choices=ESTADO_CHOICES, default='Disponible')
    fecha_ingreso_refugio = models.DateField(auto_now_add=True)

    def __str__(self):
        return self.nombre


# Tabla intermedia: Animal_Temperamentos
class AnimalTemperamento(models.Model):
    animal = models.ForeignKey(Animal, on_delete=models.CASCADE)
    temperamento = models.ForeignKey(Temperamento, on_delete=models.CASCADE)

    class Meta:
        unique_together = ('animal', 'temperamento')


# Tabla: Solicitudes de adopción
class SolicitudAdopcion(models.Model):
    ESTADO_CHOICES = [('Enviada', 'Enviada'), ('En revisión', 'En revisión'), ('Aprobada', 'Aprobada'), ('Rechazada', 'Rechazada')]

    adoptante = models.ForeignKey(Adoptante, on_delete=models.CASCADE)
    animal = models.ForeignKey(Animal, on_delete=models.CASCADE)
    fecha_solicitud = models.DateTimeField(auto_now_add=True)
    estado_solicitud = models.CharField(max_length=20, choices=ESTADO_CHOICES, default='Enviada')
    notas_del_refugio = models.TextField(blank=True)



# Tabla: Donantes
class Donante(models.Model):
    nombre_completo = models.CharField(max_length=255)
    email = models.EmailField()
    adoptante = models.OneToOneField(Adoptante, on_delete=models.SET_NULL, null=True, blank=True)
    fecha_creacion = models.DateTimeField(auto_now_add=True)

    def __str__(self):
        return self.nombre_completo


# Tabla: Donaciones
class Donacion(models.Model):
    ESTADO_CHOICES = [('Pendiente', 'Pendiente'), ('Completado', 'Completado'), ('Fallido', 'Fallido'), ('Reembolsado', 'Reembolsado')]

    donante = models.ForeignKey(Donante, on_delete=models.CASCADE)
    refugio = models.ForeignKey(Refugio, on_delete=models.SET_NULL, null=True, blank=True)
    animal = models.ForeignKey(Animal, on_delete=models.SET_NULL, null=True, blank=True)
    monto = models.DecimalField(max_digits=10, decimal_places=2)
    moneda = models.CharField(max_length=3)
    fecha_donacion = models.DateTimeField(auto_now_add=True)
    estado_pago = models.CharField(max_length=20, choices=ESTADO_CHOICES, default='Pendiente')
    gateway_transaccion_id = models.CharField(max_length=255, unique=True, null=True, blank=True)
    mensaje_donante = models.TextField(blank=True)

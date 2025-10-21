from django.contrib import admin
from django.urls import path
from rest_framework_simplejwt.views import TokenRefreshView
from administracion.views import AdminLoginView 
from drf_spectacular.views import SpectacularAPIView, SpectacularSwaggerView 

urlpatterns = [
    path('admin/', admin.site.urls),
    path('api/admin/login/', AdminLoginView.as_view(), name='admin_token_obtain_pair'),
    path('api/token/refresh/', TokenRefreshView.as_view(), name='token_refresh'),
    path('api/schema/', SpectacularAPIView.as_view(), name='schema'),     # Ruta que genera el archivo de esquema (YAML/JSON)
    path('api/schema/swagger-ui/', SpectacularSwaggerView.as_view(url_name='schema'), name='swagger-ui'),  # Ruta que muestra la interfaz interactiva (Swagger UI)
]
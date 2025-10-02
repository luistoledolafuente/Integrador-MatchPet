# administracion/serializers.py

from rest_framework_simplejwt.serializers import TokenObtainPairSerializer
from rest_framework import serializers

class AdminTokenObtainPairSerializer(TokenObtainPairSerializer):
    """
    Verifica que el usuario sea administrador (user.is_staff) antes de emitir el token.
    """
    @classmethod
    def get_token(cls, user):
        # 1. Validación de permisos
        if not user.is_staff:
            raise serializers.ValidationError(
                {"detail": "Acceso denegado. Se requieren permisos de administrador."},
                code='no_admin_access'
            )

        # 2. Si pasa, genera el token
        token = super().get_token(user)

    
        token['is_staff'] = user.is_staff
        token['email'] = user.email 
        
        return token
from django.shortcuts import render
from rest_framework_simplejwt.views import TokenObtainPairView
from .serializers import AdminTokenObtainPairSerializer

class AdminLoginView(TokenObtainPairView):
    """
    Expone el endpoint de login y usa el serializer personalizado.
    """
    serializer_class = AdminTokenObtainPairSerializer
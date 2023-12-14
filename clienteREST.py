import requests

base_url = "http://192.168.0.3:8080/TiendaREST/webresources/Producto"

def mostrar_menu():
    print("1. Listar productos")
    print("2. Buscar producto")
    print("3. Agregar producto")
    print("4. Editar producto")
    print("5. Eliminar producto")
    print("6. Salir")

def listar_productos():
    response = requests.get(f"{base_url}/listar")
    if response.status_code == 200:
        productos = response.json()
        print("Listado de productos:")
        for producto in productos:
            print(f"{producto['id']} | {producto['nombre']} | {producto['precio']} | {producto['descripcion']}")
    else:
        print(f"Error al listar productos. Código de estado: {response.status_code}")

def buscar_producto():
    producto_id = input("Ingrese el ID del producto a buscar: ")
    response = requests.get(f"{base_url}/buscar/{producto_id}")
    if response.status_code == 200:
        producto = response.json()
        if producto:
            print("Producto encontrado:")
            print(f"ID: {producto['id']}")
            print(f"Nombre: {producto['nombre']}")
            print(f"Precio: {producto['precio']}")
            print(f"Descripción: {producto['descripcion']}")
        else:
            print(f"No se encontró producto con ID {producto_id}")
    else:
        print(f"Error al buscar producto. Código de estado: {response.status_code}")

def agregar_producto():
    nombre = input("Ingrese el nombre del producto: ")
    precio = float(input("Ingrese el precio del producto: "))
    descripcion = input("Ingrese la descripción del producto: ")

    response = requests.post(f"{base_url}/guardar/{nombre}/{precio}/{descripcion}")
    if response.status_code == 200:
        print("Producto agregado exitosamente.")
    else:
        print(f"Error al agregar producto. Código de estado: {response.status_code}")

def editar_producto():
    producto_id = input("Ingrese el ID del producto a editar: ")
    nombre = input("Ingrese el nuevo nombre del producto: ")
    precio = float(input("Ingrese el nuevo precio del producto: "))
    descripcion = input("Ingrese la nueva descripción del producto: ")

    response = requests.put(f"{base_url}/editar/{producto_id}/{nombre}/{precio}/{descripcion}")
    if response.status_code == 200:
        print("Producto editado exitosamente.")
    else:
        print(f"Error al editar producto. Código de estado: {response.status_code}")

def eliminar_producto():
    producto_id = input("Ingrese el ID del producto a eliminar: ")
    response = requests.delete(f"{base_url}/eliminar/{producto_id}")
    if response.status_code == 200:
        print("Producto eliminado exitosamente.")
    else:
        print(f"Error al eliminar producto. Código de estado: {response.status_code}")

while True:
    print("-" * 30)
    mostrar_menu()
    opcion = input("Seleccione una opción: ")

    print()
    if opcion == "1":
        listar_productos()
    elif opcion == "2":
        buscar_producto()
    elif opcion == "3":
        agregar_producto()
    elif opcion == "4":
        editar_producto()
    elif opcion == "5":
        eliminar_producto()
    elif opcion == "6":
        print("Saliendo del programa. ¡Hasta luego!")
        break
    else:
        print("Opción no válida. Por favor, seleccione una opción válida.")

    print("-" * 30)
    print()

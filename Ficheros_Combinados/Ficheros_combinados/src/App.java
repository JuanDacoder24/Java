
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;
import java.util.Scanner;



public class App {
    public static void main(String[] args){
        
        Scanner teclado = new Scanner(System.in);
		String opcion = "";

		
		do { 
			System.out.println("1. Mostrar Productos en el Inventario.");
			System.out.println("2. Eliminar Producto por referencia.");
			System.out.println("3. Guardar y Salir (inventario.dat).");
			System.out.println("4. Salir");
			teclado = new Scanner(System.in);
			opcion = teclado.nextLine();

			switch (opcion) {
				case "1":
			
					break;

				case "2":
					
					break;

				case "3":
					
					break;

				case "4":
					System.out.println("Saliendo del programa, HASTA PRONTO");
					break;

				default:
					System.out.println("Elegir el numero correcto");
			}
		} while (!opcion.equalsIgnoreCase("4"));


		LinkedList<Producto> inventario = new LinkedList<>();
		

		try (FileReader file = new FileReader("Recursos\\productos.csv"); BufferedReader buffer = new BufferedReader(file)) {

			String linea = null;
					do {
						linea = buffer.readLine();
						if(linea != null) {
							String [] elementos = linea.split("/");
							Producto p = new Producto(elementos[0], elementos[1], elementos[2], elementos[3]);
							inventario.add(p);							
						}
					}while(linea != null);
					
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}

		//mostrar productos
		
		for (Producto p : inventario) {
			System.out.println(p);
		}

		boolean eof = false;
        try (FileInputStream fichero = new FileInputStream("Recursos\\almacen.dat"); DataInputStream lector = new DataInputStream(fichero)) {
            while(!eof){
                int cantidad = lector.readInt();
				double precio = lector.readDouble();
				int descuento = lector.readInt();
				int iva = lector.readInt();
				boolean aplicarDto = lector.readBoolean();
				Producto p = new Producto(cantidad, precio, descuento, iva, aplicarDto);
				inventario.add(p);
            }
            
        } catch (EOFException e) {
            eof = true;
			
        }
        catch (IOException e){
            System.out.println(e.getMessage());
        }


		try (FileOutputStream fichero = new FileOutputStream("Recursos\\almacen.dat", false); DataOutputStream escritor = new DataOutputStream(fichero)) {

            for (Producto p : inventario) {
				escritor.writeInt(p.getCantidad());
				escritor.writeDouble(p.getPrecio());
				escritor.writeInt(p.getDescuento());
				escritor.writeInt(p.getIva());
				escritor.writeBoolean(p.isAplicarDto());
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

		
	}

}



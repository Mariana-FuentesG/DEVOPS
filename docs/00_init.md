on 

# Pasos ejecutados

## Docker instalación

Windows: https://www.youtube.com/watch?v=cWuirzMOwwg

Ubuntu/Linux: https://www.youtube.com/watch?v=9Q6TrR5YO3Q

## Paso 01

### Iniciar docker desktop

Luego de iniciar docker desktop aparece asi:

![1787433058438](image/00_init/1787433058438.png)

### Instalar y luego probar git

![1787434636794](image/00_init/1787434636794.png)

### Configurar Git

![1787434818664](image/00_init/1787434818664.png)

## Paso 02 - probar el microservicio localmente

Si se ejecuta el comando sacando el "--build -d" se p uede ver como se inicializa en pantalla:

![1787437413036](image/00_init/1787437413036.png)

![1787437427256](image/00_init/1787437427256.png)

En docker destop se puede ver en contenedores:

![1787437487611](image/00_init/1787437487611.png)

y en imagenes:

![1787437513242](image/00_init/1787437513242.png)

Se puede ver ya en la web puerto 8080:

![1787437560613](image/00_init/1787437560613.png)

![1787437570315](image/00_init/1787437570315.png)

![1787437586055](image/00_init/1787437586055.png)

## Paso 03 - Repo Git

Primero creamos el archivo .gitignore en visual code

![1787437897166](image/00_init/1787437897166.png)

Luego ya en la terminal:

```
git init -b main
```

esto crea una carpeta llamaga .git y adentro estar la información para hacer el seguimiento de los cambios en el codigo.

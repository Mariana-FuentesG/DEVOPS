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

```
git add .
git commit -m "feat: version inicial del microservicio pagos"
```

![1787438092848](image/00_init/1787438092848.png)

### Creación de token en github

Esto permite subir contenido a github de forma directa sin exponer directamente tu contrasena:

Primero se debe seleccionar Setting que esta al costado donde esta el icono del usuario:

![1787438481512](image/00_init/1787438481512.png)

Segundo se debe ir a la ultima opción

![1787438563337](image/00_init/1787438563337.png)

Tercero se debe seleccionar Personal access token y luego tokens (classic)

![1787438640394](image/00_init/1787438640394.png)

Cuarto, seleccionar Generate new token (classic)

![1787438661567](image/00_init/1787438661567.png)

Quinto, verificacion por correo:

![1787438699462](image/00_init/1787438699462.png)

Sexto: poner nombre al token, una fecha lejana y seleccionar todos los permisos:

![1787438884010](image/00_init/1787438884010.png)

Septimo: se genera el token y ese se debe guardar

![1787438917426](image/00_init/1787438917426.png)


Crear el repositorio en github

![1787438308221](image/00_init/1787438308221.png)

Luego como esta vacio el repositorio aparece esto:

![1787439171225](image/00_init/1787439171225.png)

Ahora se ingresan los siguientes comandos

```
git remote add origin https://github.com/mauriciovelasquezduoc/202602_ING_DEVOPS_S02.git
git branch -M main
git push -u origin main
```

![1787439142780](image/00_init/1787439142780.png)

* Cuando se realice `git push -u origin main` solicitara el username y la passwrod debe ingresar el token

Finalmente ya queda el repositorio arriba en github

![1787439300189](image/00_init/1787439300189.png)

## Paso 04: Ramas

Antes de la creacion de las ramas, y al hacer click en main no aparecne ramas, pero mas adelante ya se veran:

![1787439423785](image/00_init/1787439423785.png)

Crear las ramas:

```
git checkout -b develop 
git push -u origin develop 
git checkout main
```

![1787439496434](image/00_init/1787439496434.png)

Ahora en github se ve develop

![1787439527408](image/00_init/1787439527408.png)

## Paso 05 : modelo de ramificacion

Se procede a editar el archivo README.md en visual code

![1787439771306](image/00_init/1787439771306.png)

 Y se lanzan los comandos:

![1787439826852](image/00_init/1787439826852.png)

## Paso 06 Feature 1

```
git checkout develop
git pull origin develop 
git checkout -b feature/pagina-presentacion
```

![1787440037284](image/00_init/1787440037284.png)

Ahora se modifica el footer para identificar un cambio en la feature

![1787440129483](image/00_init/1787440129483.png)

Ahora se sube a github a la rama de feature con los siguientes comandos:

```
git add src/main/resources/static/index.html
git commit -m "feat(ui): agregar pie de pagina con version del servicio"
git push -u origin feature/pagina-presentacion
```

Con esto hemos realizado nuestro primer pull requeset que otra persona debe aprobar antes de fucionar en develop

![1787440239104](image/00_init/1787440239104.png)

Ahora tambien en github se ve la rama feature:

![1787440269198](image/00_init/1787440269198.png)

Ahora se debe proceder a llevar estos cambios a development:

1. Pull request
2. Seleccionar el boton "New pull request"
3. Ahora se debe buscar la rama feature y compararlo con development
4. Luego se vanaza con "Create pull request"
5. Pull request
6. Si todo eseta ok se puede hacer Merge
7. Confirmar

![1787440517392](image/00_init/1787440517392.png)

![1787440588945](image/00_init/1787440588945.png)

Sincronizar con develop

```
git checkout develop
git pull origin develop
```

![1787440722193](image/00_init/1787440722193.png)

## Paso 07 Feature 2

Ahora el objetivo es que tu companero realice otra modificacion

```
git checkout develop
git pull origin develop
git checkout -b feature/changelog
```

![1787440945799](image/00_init/1787440945799.png)

Creo el archivo Changelog y pone contenido:

![1787441002419](image/00_init/1787441002419.png)

```
git add CHANGELOG.md
git commit -m "docs: agregar changelog del microservicio pagos"
git push -u origin feature/changelog
```

![1787441057686](image/00_init/1787441057686.png)

Ahora en github aparece la rama:

![1787441085596](image/00_init/1787441085596.png)

Y ahora lo llevamos a develop

![1787441132780](image/00_init/1787441132780.png)

![1787441158507](image/00_init/1787441158507.png)

## Paso 08: hotfix

```
git checkout main
git pull origin main
git checkout -b hotfix/titulo-pagina
```

Hacer un cambio en la pagina index:

![1787441401159](image/00_init/1787441401159.png)

```
git add src/main/resources/static/index.html
git commit -m "fix(ui): corregir titulo de la pagina principal"
git push -u origin hotfix/titulo-pagina
```

![1787441458162](image/00_init/1787441458162.png)

![1787441500815](image/00_init/1787441500815.png)

![1787441532862](image/00_init/1787441532862.png)

## Paso 10: Github Action

Usando visual code se debe crear un archivo .github/workflows/hola-mundo.yml

![1787441778087](image/00_init/1787441778087.png)

Ahora lo pasamos a develop

```
git add .github/workflows/hola-mundo.yml
git commit -m "chore(ci): agregar workflow hola mundo (IE3/IE4)"
git push origin develop
```

![1787441848169](image/00_init/1787441848169.png)

En github si se hace click en Actios , se puede ver el wf agregado

![1787441916394](image/00_init/1787441916394.png)

Al hacer click en el comentario se puede ver el detalle

![1787441947554](image/00_init/1787441947554.png)

Como se observa la ejec quedo ok (color verde) y luego uno puede entrar y ahi podemos ver el mensaje de hola mundo

![1787442000880](image/00_init/1787442000880.png)

aaa

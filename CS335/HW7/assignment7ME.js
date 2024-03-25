// Mai Evans HW7 

function init() {
	var scene = new THREE.Scene();

	var lightOn = false;

	var box = getBox(1, 1, 1, 'rgb(92,60,51)');
	var box2 = getBox(0.75, 0.75, 0.75, 'rgb(0,50,139)');
	var box3 = getBox(0.5, 0.5, 0.5, 'rgb(1,40,32)');
	
	var plane = getPlane(20);
	
	var pointLight = getPointLight(1);
	var pointLight2 = getPointLight(1);
	var pointLight3 = getPointLight(1);
	var pointLight4 = getPointLight(1);
	
	var sphere = getSphere(0.05);
	var sphere2 = getSphere(0.05);
	var sphere3 = getSphere(0.05);
	var sphere4 = getSphere(0.05);
	var sphereTop = getSphereTop(0.5);	

	plane.name = 'plane-1';

	box.position.y = box.geometry.parameters.height/2;
	box2.position.y = (box2.geometry.parameters.height/2 + box.geometry.parameters.height);
	box3.position.y = (box3.geometry.parameters.height/2 + box2.geometry.parameters.height + box.geometry.parameters.height);
	sphereTop.position.y = (sphereTop.geometry.parameters.radius + box3.geometry.parameters.height + box2.geometry.parameters.height + box.geometry.parameters.height);
	plane.rotation.x = Math.PI/2;
	
	pointLight.position.set(10,2,-10);
	pointLight2.position.set(10,2,10);
	pointLight3.position.set(-10,2,10);
	pointLight4.position.set(-10,2,-10);

	scene.add(box);
	scene.add(box2);
	scene.add(box3);
	scene.add(plane);
	
	pointLight.add(sphere);
	pointLight2.add(sphere2);
	pointLight3.add(sphere3);
	pointLight4.add(sphere4);
	scene.add(sphereTop);
	
	scene.add(pointLight);
	scene.add(pointLight2);
	scene.add(pointLight3);
	scene.add(pointLight4);
	
	document.onkeydown = function(ev) {
		switch(ev.code) {
			case "Digit1": 
				lightOn = !lightOn;
				if(lightOn) {
					pointLight.intensity = 0;
				} else {
					pointLight.intensity = 1;
				}
				break;
			
			case "Digit2": 
				lightOn = !lightOn;
				if(lightOn) {
					pointLight2.intensity = 0;
				} else {
					pointLight2.intensity = 1;
				}
				break;
			
			case "Digit3": 
				lightOn = !lightOn;
				if(lightOn) {
					pointLight3.intensity = 0;
				} else {
					pointLight3.intensity = 1;
				}
				break;

			case "Digit4": 
				lightOn = !lightOn;
				if(lightOn) {
					pointLight4.intensity = 0;
				} else {
					pointLight4.intensity = 1;
				}
				break;
				
				//camera movement
			case 'ArrowLeft':
					camera.position.x -= 0.1;
					break;
			case 'ArrowRight':
					camera.position.x += 0.1;
					break;
			case 'ArrowUp':
					camera.position.z -= 0.1;
					break;
			case 'ArrowDown':
					camera.position.z += 0.1;
					break;
			case 'KeyJ':
					camera.position.y += 0.1;
					break;
			case 'KeyK':
					camera.position.y -= 0.1;
					break;
		}       
	};
	
 
	var camera = new THREE.PerspectiveCamera(
		45,
		window.innerWidth/window.innerHeight,
		1,
		1000
	);

	camera.position.x = 1;
	camera.position.y = 2;
	camera.position.z = 10;

// 	var textureLoader = new THREE.TextureLoader();
// var texture = textureLoader.load('.jpg');

	var renderer = new THREE.WebGLRenderer();
	renderer.setSize(window.innerWidth, window.innerHeight);
	renderer.setClearColor('rgb(120, 120, 120)');
	document.getElementById('webgl').appendChild(renderer.domElement);

	update(renderer, scene, camera);

	return scene;
}



function getBox(w, h, d, color) {
	var geometry = new THREE.BoxGeometry(w, h, d);
	var material = new THREE.MeshPhongMaterial({
		color: color
	});
	var mesh = new THREE.Mesh(
		geometry,
		material 
	);

	return mesh;
}

function getPlane(size) {
	var geometry = new THREE.PlaneGeometry(size, size);
	var material = new THREE.MeshPhongMaterial({
		color: 'rgb(120, 120, 120)',
		side: THREE.DoubleSide
	});
	var mesh = new THREE.Mesh(
		geometry,
		material 
	);

	return mesh;
}

function getSphere(size) {
	var geometry = new THREE.SphereGeometry(size, 24, 24);
	var material = new THREE.MeshBasicMaterial({
		color: 'rgb(255, 255, 255)'
	});
	var mesh = new THREE.Mesh(
		geometry,
		material 
	);

	return mesh;
}

function getSphereTop(size) {
	var geometry = new THREE.SphereGeometry(size, 24, 24);
	var material = new THREE.MeshPhongMaterial({color: 'rgb(128, 128, 128)'});
	var mesh = new THREE.Mesh(geometry,material );

	return mesh;
}

function getPointLight(intensity) {
	var light = new THREE.PointLight(0xffffff, intensity);

	return light;
}

function update(renderer, scene, camera) {
	
	camera.position.set = (0,2,15);
	camera.lookAt(scene.position);

	renderer.render(scene,camera);

	requestAnimationFrame(function() {update(renderer, scene, camera);})
}
var scene = init();
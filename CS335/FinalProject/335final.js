// Mai Evans Final Project 

function init() {
  //-- Initialize Scene --//
  var scene = new THREE.Scene();
 
  //-- Import Textures --//
  const backgroundTex = new THREE.TextureLoader().load('Pictures/background.jpg');
  const planeTexture = new THREE.TextureLoader().load('Pictures/sand.jpg');
  const blueTexture = new THREE.TextureLoader().load('Pictures/coralblue.jpg');
  const pinkTexture = new THREE.TextureLoader().load('Pictures/coralpink.jpg');
  const cyanTexture = new THREE.TextureLoader().load('Pictures/coralcyan.jpg');
  const brainTexture = new THREE.TextureLoader().load('Pictures/braincoral.jpg');
  const seaweedTexture = new THREE.TextureLoader().load('Pictures/seaweed.jpg');

  scene.background = backgroundTex; //Apply background to scene

  //-- Plane --//
  var plane = getPlane(20, planeTexture);
  plane.name = 'plane-1';
  plane.rotation.x = Math.PI/2;
	scene.add(plane);

  //-- Lighting --//
  //Directional Light
  const directionalLight = new THREE.DirectionalLight( 0xffffff, 0.3 );
	directionalLight.position.set(10,10,10);
	scene.add(directionalLight);

  //Ambient Light
	const ambientLight = new THREE.AmbientLight( 0x404040 ); 
	scene.add(ambientLight);

  //Positional Lights
  var pointLight = getPointLight(1);
  pointLight.position.set(10,2,-10);
  scene.add(pointLight);

  var pointLight2 = getPointLight(1);
  pointLight2.position.set(-10,2,10);
  scene.add(pointLight2);

  //Spot Light
	var spotLight = getSpotLight();
	spotLight.position.set( -1, 2, 5 );
	spotLight.target.position.set(-1, 0, 0); 
	spotLight.angle = Math.PI / 5;
	scene.add(spotLight); 
	scene.add(spotLight.target);

  //-- Coral Box Objects --//
  //Blue
	var box = getBox(1, 1, 1, blueTexture);
	var box2 = getBox(1, 1, 1, blueTexture);
	var box3 = getBox(1, 1, 1, blueTexture);
  box.position.set(-8, box.geometry.parameters.height/2, -4);
	box2.position.set(-8, box2.geometry.parameters.height/2 + box.geometry.parameters.height, -4);
	box3.position.set(-8, box3.geometry.parameters.height/2 + box2.geometry.parameters.height + box.geometry.parameters.height, -4);
  scene.add(box);
	scene.add(box2);
	scene.add(box3);
  //Pink
  var box4 = getBox(1, 1, 1, pinkTexture);
	var box5 = getBox(1, 1, 1, pinkTexture);
	var box6 = getBox(1, 1, 1, pinkTexture);
  box4.position.set(7, box.geometry.parameters.height/2, 1);
	box5.position.set(7, box2.geometry.parameters.height/2 + box.geometry.parameters.height, 1);
	box6.position.set(7, box3.geometry.parameters.height/2 + box2.geometry.parameters.height + box.geometry.parameters.height, 1);
  scene.add(box4);
	scene.add(box5);
  scene.add(box6);
  //Cyan
  var box7 = getBox(1, 1, 1, cyanTexture);
	var box8 = getBox(1, 1, 1, cyanTexture);
	var box9 = getBox(1, 1, 1, cyanTexture);
  box7.position.set(-5, box.geometry.parameters.height/2, 4);
	box8.position.set(-5, box2.geometry.parameters.height/2 + box.geometry.parameters.height, 4);
	box9.position.set(-5, box3.geometry.parameters.height/2 + box2.geometry.parameters.height + box.geometry.parameters.height, 4);
  scene.add(box7);
	scene.add(box8);
  scene.add(box9);

  //-- Floating Seaweed Box --//
  var seaweed = getBox(1, 1, 1, seaweedTexture);
  var seaweedY = 0.5;
	var seaweedMove = 1;
  seaweed.position.set(3,2,5);
  scene.add(seaweed);
  //Floating Function 
  function seaweedFloat() {
    seaweedY += seaweedMove * 0.1;
    
    if (seaweedY > 4.5) {
      seaweedMove = -0.35;
    
    } else if (seaweedY < 1) {
      seaweedMove = 0.35;
    }
    seaweed.position.y = seaweedY;
  }
  
  //-- JellyFish Top and Bottom --//
  var JellyTop = getJellyTop(1);	
  JellyTop.position.set(0,3.5,0);
  scene.add(JellyTop);
  
  var JellyBottom = getJellyBottom(1,0.1);
  JellyBottom.position.set(0,3.5,0);
  scene.add(JellyBottom);
	
	//-- Jelly Tentacles --//
  var tentacle1 = getTentacle(0.1,2.5);
  var tentacle2 = getTentacle(0.1,2.5);
  var tentacle3 = getTentacle(0.1,2.5);
  var tentacle4 = getTentacle(0.1,2.5);
  var tentacle5 = getTentacle(0.1,2.5);
  
  tentacle1.position.set(0.5,3,0);
  tentacle2.position.set(-0.5,3,0);
  tentacle3.position.set(-0.5,2.5,0.5);
  tentacle4.position.set(0.5,2.5,-0.5);
  tentacle5.position.set(0,2.7,0);
  
  scene.add(tentacle1);
  scene.add(tentacle2);
  scene.add(tentacle3);
  scene.add(tentacle4);
  scene.add(tentacle5);

  //-- BrainCoral Sphere --//
  var BrainCoral = getBrainCoral(1, brainTexture);
  BrainCoral.position.set(5,0,-5);
  scene.add(BrainCoral);

  const LookAt = new THREE.Vector3(0, 0, 0);

  //-- Movement Key Cases --//
	document.onkeydown = function(ev) {
		switch(ev.code) {
			// lightON/OFF
      case "Digit1": 
					  ambientLight.visible = !ambientLight.visible; //Ambient
            break;
			case "Digit2": 
					  directionalLight.visible = !directionalLight.visible; //Directional
            break;
			case "Digit3": 
					  spotLight.visible = !spotLight.visible; //Spotlight
            break;
			case "Digit4": 
					  pointLight.visible = !pointLight.visible; //Pointlight
            break;
      case "Digit5": 
            pointLight2.visible = !pointLight2.visible; //Pointlight
            break;
				
      // LookAt Movement
      case 'KeyA': 
					LookAt.z -= 0.5; 
					break;
										
			case 'KeyS': 
					LookAt.z += 0.5;
					break;
							
			case 'KeyW': 
					LookAt.y += 0.5;
					break;
							
			case 'KeyD': 
					LookAt.y -= 0.5;
					break;

			// Camera Movement
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
		}      camera.lookAt(LookAt)
    
	};
	
 //-- Camera Stuff --//
	var camera = new THREE.PerspectiveCamera(45,window.innerWidth/window.innerHeight,1,1000);

	camera.position.x = 3;
	camera.position.y = 2;
	camera.position.z = 15;

  camera.lookAt(new THREE.Vector3(0, 0, 0));

  //-- Render Stuff --//
	var renderer = new THREE.WebGLRenderer();
	renderer.setSize(window.innerWidth, window.innerHeight);
	renderer.setClearColor('rgb(120, 120, 120)');
	document.getElementById('webgl').appendChild(renderer.domElement);

  function animate() {
    requestAnimationFrame(animate);
    seaweedFloat();
    renderer.render(scene, camera);
  }
  animate();

}

// -- Object/Shape Functions --//

function getPlane(size, texture) {
	var geometry = new THREE.PlaneGeometry(size, size);
	var material = new THREE.MeshLambertMaterial( {map: texture, side: THREE.DoubleSide});
	var mesh = new THREE.Mesh(geometry, material);

	return mesh;
}

function getPointLight(intensity) {
	var light = new THREE.PointLight(0xffffff, intensity);

	return light;
}

function getSpotLight(){
	var light = new THREE.SpotLight( 0xffffff, 5);
  
  return light;
}

function getBox(w, h, d, texture) {
	var geometry = new THREE.BoxGeometry(w, h, d, 50);
	var material = new THREE.MeshLambertMaterial({map: texture});
	var mesh = new THREE.Mesh(geometry,material );

	return mesh;
}

function getJellyTop(size){
  
  const phiStart = 0;
  const phiEnd = Math.PI * 2;
  const thetaStart = 0;
  const thetaEnd = Math.PI / 2;

  const geometry = new THREE.SphereGeometry( size, 24, 24, phiStart, phiEnd, thetaStart, thetaEnd );
  var material = new THREE.MeshPhongMaterial({color: 'rgb(117, 232, 234)'});
  var mesh = new THREE.Mesh(geometry,material);
  return mesh;
  }

function getJellyBottom(radius, height){
	var geometry = new THREE.CylinderGeometry(radius, radius, height, 24, 1);
  var material = new THREE.MeshPhongMaterial({color: 'rgb(117, 232, 234)'});
	var mesh = new THREE.Mesh(geometry,material);
	return mesh;
}

function getTentacle(radius, height){
	var geometry = new THREE.CylinderGeometry(radius, radius, height, 30, 30);
	var material= new THREE.MeshPhongMaterial({color: 'rgb(117, 232, 234)'});
	var mesh = new THREE.Mesh(geometry,material);

  return mesh;
}

function getBrainCoral(size, texture){
  const phiStart = 0;
  const phiEnd = Math.PI * 2;
  const thetaStart = 0;
  const thetaEnd = Math.PI / 2;

  const geometry = new THREE.SphereGeometry( size, 24, 24, phiStart, phiEnd, thetaStart, thetaEnd );
  var material = new THREE.MeshLambertMaterial( {map: texture, side: THREE.DoubleSide});
  var mesh = new THREE.Mesh(geometry,material);
  return mesh;
}
var scene = init();
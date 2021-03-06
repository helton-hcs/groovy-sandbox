class Phone {
	String dial(number) {
		"dialing $number"
	}
	String getManufacturer() { "Samsumg" }
}

class Camera {
	String takePicture() {
		"taking picture"
	}
	String getManufacturer() { "Nikon" }
}

class SmartPhone {
	@Delegate Phone phone = new Phone()
	@Delegate Camera camera = new Camera()
	String getManufacturer() {
		"${phone.manufacturer()}, ${camera.manufacturer()}"
	}
}

SmartPhone sp = new SmartPhone()
assert sp.dial('555-1234') == 'dialing 555-1234'
assert sp.takePicture() == 'taking picture'
println sp.getManufacturer()
#version 330

in vec2 position;
in vec2 textCoord;

out vec2 textCoord0;

uniform mat4 transform;

void main(){
	textCoord0 = textCoord;
	gl_Position = transform * vec4(position, 0.0, 1.0);
}
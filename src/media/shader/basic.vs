#version 150 core

in vec2 position;
in vec2 textCoord;

out vec2 textCoord0;

uniform mat4 transform;

void main(){
	gl_Position = transform * vec4(position, 0, 1);
	textCoord0 = textCoord;
}
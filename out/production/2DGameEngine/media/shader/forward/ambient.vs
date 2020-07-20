#version 150 core

in vec2 position;
in vec2 textCoord;

out vec2 textCoord0;

uniform mat4 transformProjected;

void main(){
	gl_Position = transformProjected * vec4(position, 0, 1);
	textCoord0 = textCoord;
}
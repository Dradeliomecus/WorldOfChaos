#version 120

attribute vec2 position;
attribute vec2 textCoord;

varying vec2 textCoord0;

uniform mat4 transformProjected;

void main(){
	textCoord0 = textCoord;
	gl_Position = transformProjected * vec4(position, 0.0, 1.0);
}
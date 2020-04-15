#version 330

attribute vec2 position;
attribute vec2 textCoord;

out vec2 worldPos0;
out vec2 textCoord0;

uniform mat4 transform;
uniform mat4 transformProjected;

void main(){
	worldPos0 = (transform * vec4(position, 0.0, 1.0)).xy;
	textCoord0 = textCoord;
	gl_Position = transformProjected * vec4(position, 0.0, 1.0);
}
#version 150 core

in vec2 position;
in vec2 textCoord;

out vec2 worldPos0;
out vec2 textCoord0;
out vec4 out_color;

uniform mat4 transform;
uniform mat4 transformProjected;

void main(){
	worldPos0 = (transform * vec4(position, 0, 1)).xy;
	textCoord0 = textCoord;
	out_color = transformProjected * vec4(position, 0, 1);
}
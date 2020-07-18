#version 330

in vec2 position;
in vec2 textCoord;

out vec2 textCoord0;

uniform mat4 transformProjected;

void main(){
	textCoord0 = textCoord;
	gl_Position = transformProjected * vec4(position, 0.0, 1.0);
}
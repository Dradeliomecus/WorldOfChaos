#version 330

in vec2 textCoord0;

out vec4 color;

uniform sampler2D sampler;

void main(){
	color = texture2D(sampler, textCoord0);
}
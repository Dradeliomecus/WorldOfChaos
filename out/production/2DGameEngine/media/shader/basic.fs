#version 330

in vec2 textCoord0;

out vec4 out_color;

uniform vec4 color;
uniform sampler2D sampler;

void main(){
	out_color = color * texture(sampler, textCoord0);
}
#version 330

in vec2 textCoord0;

uniform vec4 color;
uniform sampler2D sampler;

void main(){
	gl_FragColor = color * texture2D(sampler, textCoord0);
}
#version 330

varying vec2 textCoord0;

uniform sampler2D sampler;

void main(){
	gl_FragColor = texture2D(sampler, textCoord0);
}
#version 120

varying vec2 worldPos0;
varying vec2 textCoord0;

#include "forward/lightning.fsh"

uniform vec4 materialColor;
uniform sampler2D sampler;
uniform PointLight pointLight;

void main(){
	gl_FragColor = materialColor * texture2D(sampler, textCoord0) * calculatePointLight(pointLight);
}
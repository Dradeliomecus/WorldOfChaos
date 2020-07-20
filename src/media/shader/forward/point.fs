#version 150 core

in vec2 worldPos0;
in vec2 textCoord0;

out vec4 out_color;

#include "forward/lightning.fsh"

uniform vec4 materialColor;
uniform sampler2D sampler;
uniform PointLight pointLight;

void main(){
	out_color = materialColor * texture(sampler, textCoord0) * calculatePointLight(pointLight);
}
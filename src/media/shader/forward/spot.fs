#version 120

struct SpotLight {
	PointLight pointLight;
	vec2 direction;
	float cutoff;
};

in vec2 worldPos0;
in vec2 textCoord0;

out vec4 out_color;

#include "forward/lightning.fsh"

uniform vec4 materialColor;
uniform sampler2D sampler;
uniform SpotLight spotLight;


vec4 calculateSpotLight(SpotLight spotLight) {
	vec2 lightDirection = normalize(worldPos0 - spotLight.pointLight.position);
	float spotFactor = dot(lightDirection, spotLight.direction);

	vec4 color = vec4(0, 0, 0, 0);

	if(spotFactor > spotLight.cutoff) {
		color = calculatePointLight(spotLight.pointLight) * (1.0 - (1.0 - spotFactor) / (1.0 - spotLight.cutoff));
	}

	return color;
}

void main(){
	out_color = materialColor * texture2D(sampler, textCoord0) * calculateSpotLight(spotLight);
}
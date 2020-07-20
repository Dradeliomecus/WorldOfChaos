#version 330

const int MAX_POINT_LIGHTS = 4;
const int MAX_SPOT_LIGHTS = 4;

struct BaseLight {
	vec4 color;
	float intensity;
};

struct Attenuation {
	float constant;
	float linear;
	float exponent;
};

struct PointLight {
	BaseLight base;
	Attenuation attenuation;
	vec2 position;
	float range;
};

struct SpotLight {
	PointLight pointLight;
	vec2 direction;
	float cutoff;
};

in vec2 worldPos0;
in vec2 textCoord0;

out vec4 out_color;

uniform vec4 baseColor;
uniform vec4 ambientLight;
uniform sampler2D sampler;

uniform PointLight pointLights[MAX_POINT_LIGHTS];
uniform SpotLight spotLights[MAX_SPOT_LIGHTS];

vec4 calculatePointLight(PointLight pointLight) {
	float distanceToPoint = length(worldPos0 - pointLight.position);

	if(distanceToPoint > pointLight.range) {
		return vec4(0, 0, 0, 0);
	}

	float attenuation = pointLight.attenuation.constant +
						pointLight.attenuation.linear * distanceToPoint +
						pointLight.attenuation.exponent * distanceToPoint * distanceToPoint + 0.0001;

	//return (pointLight.base.color * pointLight.base.intensity) / attenuation;
	return (pointLight.base.color * pointLight.base.intensity) / (attenuation + 1);
}

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
	vec4 color = baseColor * texture(sampler, textCoord0);
	vec4 totalLight = ambientLight;

	for(int i = 0; i < MAX_POINT_LIGHTS; i++) {
		if(pointLights[i].base.intensity > 0) {
			totalLight += calculatePointLight(pointLights[i]);
		}
	}

	for(int i = 0; i < MAX_SPOT_LIGHTS; i++) {
		if(spotLights[i].pointLight.base.intensity > 0) {
			totalLight += calculateSpotLight(spotLights[i]);
		}
	}

	out_color = color * totalLight;
}
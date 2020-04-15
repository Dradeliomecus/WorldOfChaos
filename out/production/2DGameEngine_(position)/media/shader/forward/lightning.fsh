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

vec4 calculatePointLight(PointLight pointLight) {
	float distanceToPoint = length(worldPos0 - pointLight.position);

	if(distanceToPoint > pointLight.range) {
		return vec4(0, 0, 0, 0);
	}

	float attenuation = pointLight.attenuation.constant +
						pointLight.attenuation.linear * distanceToPoint +
						pointLight.attenuation.exponent * distanceToPoint * distanceToPoint + 0.000001;

	return (pointLight.base.color * pointLight.base.intensity) / attenuation;
}
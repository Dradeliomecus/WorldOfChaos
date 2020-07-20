#version 150 core

in vec2 textCoord0;

out vec4 out_color;

uniform vec4 materialColor;
uniform vec4 ambientLight;
uniform sampler2D sampler;

void main(){
	if(texture(sampler, textCoord0).a == 0)
		discard;

	out_color = materialColor * ambientLight * texture(sampler, textCoord0);
	//gl_FragColor = vec4(materialColor.r, materialColor.g, materialColor.b, 0.5) * ambientLight * texture2D(sampler, textCoord0);
	//gl_FragColor = ambientLight * vec4(1, 0, 0, texture2D(sampler, textCoord0).a);
}
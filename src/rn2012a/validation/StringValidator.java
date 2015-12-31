package rn2012a.validation;

import com.opensymphony.xwork2.validator.ValidationException;
import com.opensymphony.xwork2.validator.validators.FieldValidatorSupport;

public class StringValidator extends FieldValidatorSupport {

	private String pattern;

	public String getPattern() {
		return pattern;
	}

	public void setPattern(String pattern) {
		this.pattern = pattern;
	}

	@Override
	public void validate(Object object) throws ValidationException {

		//1. 获取字段的名字和值
		String fieldName = getFieldName();
        Object value = this.getFieldValue(fieldName, object);

		//2. 验证
        boolean result = pattern.equals(value);
        
		//3. 若验证失败，则。。。。
		if (!result) {
			addFieldError(fieldName, object);
		}
		
	}

}

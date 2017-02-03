package com.jenzz.pojobuilder.processor.rules;

import com.jenzz.pojobuilder.processor.expections.PrivateFieldException;
import com.jenzz.pojobuilder.processor.expections.RuleException;

import javax.lang.model.element.Element;
import javax.lang.model.element.VariableElement;

import static com.jenzz.pojobuilder.processor.BuilderProcessor.ANNOTATION;
import static javax.lang.model.element.Modifier.FINAL;
import static javax.lang.model.element.Modifier.STATIC;
import static javax.lang.model.util.ElementFilter.fieldsIn;

public class NonStaticFinalFieldsRule implements Rule {

  private VariableElement field;

  @Override
  public void validate(Element element) throws RuleException {
    for (VariableElement field : fieldsIn(element.getEnclosedElements())) {
      boolean isStatic = field.getModifiers().contains(STATIC);
      boolean isFinal = field.getModifiers().contains(FINAL);
      if (isStatic && isFinal) {
//        throw exception();
      }
    }
  }

  @Override
  public RuleException exception() {
    return new PrivateFieldException(
        "Class annotated with " + ANNOTATION + " has static final field " + field.getSimpleName() + ". "
            + "Add @Ignore annotation or make field at least package-private.");
  }
}

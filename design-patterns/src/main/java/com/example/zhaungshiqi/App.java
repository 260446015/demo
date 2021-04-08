package com.example.zhaungshiqi;

public class App {
    public static void main(String[] args) {
        Component c1 = new ConcreteComponent();
        Decorator decoratorA = new ConcreteDecoratorA(c1);
        decoratorA.operation();
        Decorator decoratorB = new ConcreateDecoratorB(c1);
        decoratorB.operation();
        Decorator decoratorBandA = new ConcreateDecoratorB(decoratorA);
        decoratorBandA.operation();
    }
    static abstract class Component{
        public abstract void operation();
    }

    static class ConcreteComponent extends Component{
        public void operation(){
            System.out.println("处理业务逻辑");
        }
    }
    static abstract class Decorator extends Component{
        protected Component component;

        public Decorator(Component component) {
            this.component = component;
        }
        public void operation(){
            component.operation();
        }
    }
    static class ConcreteDecoratorA extends Decorator{
        public ConcreteDecoratorA(Component component) {
            super(component);
        }
        private void operationFirst(){}
        private void operationLast(){}

        @Override
        public void operation() {
            operationFirst();
            super.operation();
            operationLast();
        }
    }
    static class ConcreateDecoratorB extends Decorator{
        public ConcreateDecoratorB(Component component) {
            super(component);
        }
        private void operationFirst(){}
        private void operationLast(){}
        public void operation(){
            operationFirst();
            super.operation();
            operationLast();
        }
    }
}

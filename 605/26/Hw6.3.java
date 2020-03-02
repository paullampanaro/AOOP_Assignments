/*
public class Hw6.3 {
    1. Give an example where you would use an interface and not an abstract class.

  -> Lets consider a Billing class such that payment can be made in many forms, such as Tigerbucks, credit card,...
  We will take Billing class as our interface consisting  a doBilling() method and CreditCard and Tigerbucks are
  implementing them.

    public interface Billing
    {
        void doBilling();//  Abstract method(Default)
    }
    public class TigerBucks implements Billing
    {
        public void doBilling()
        {
            //TigerBucks payment technique
            //e.g. using RIT login information for payments
        }
    }
    public class CreditCard implements Billing
    {
        public void doBilling()
        {
            //CreditCard payment technique
            //using CC info for payment
        }
    }
    The given example  shows implementation  of CreditCard and Tigerbucks. In interface multiple inheritance can be
    performed which is restricted in an abstract class.

2. Give an example where you would use an an abstract class and not an interface

   -> Lets choose an abstract class where there are some features for which we know its function, and other
   features that we know how to perform.

        Example:

    public abstract class Taco
    {
        public void packing()
        {
            //Technique for packing a taco
        }
        public abstract void price(); //Different prices for Varities of Tacos.
    }
    public class VegTaco extends Taco
    {
        public void price()
        {
            //set price for VegTaco.
        }
    }
    public class MeatTaco extends Taco
    {
        public void price()
        {
            //set price for a MeatTaco.
        }
    }
    Adding any methods in the future to a given abstract class needs no change in the implementing class. However,
    adding method in an interface class in the future ,needs its implementation in the subclasses or otherwise causing
    compiler error..
}

 */

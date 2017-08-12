package com.example.joshi.can;

import com.example.joshi.can.Exception.XMustBeLargerThanZeroException;
import com.example.joshi.can.Exception.YMustBeLargerThanZeroException;
import com.example.joshi.can.Logic.Corner;
import com.example.joshi.can.Logic.Node;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    @Test
    public void addition_isCorrect() throws Exception {
        assertEquals(4, 2 + 2);
    }


    @Test
    public void test_Node_checkIfInMyZone_True()
    {
        Node node = new Node();
        try {
            Corner bottomLeftCorner = new Corner(0.3, 0.1);
            Corner bottomRightCorner= new Corner(0.6, 0.1);
            Corner topLeftCorner    = new Corner(0.3, 0.9);
            Corner topRightCorner   = new Corner(0.6, 0.9);
            node.setBottomLeftCorner(bottomLeftCorner);
            node.setBottomRightCorner(bottomRightCorner);
            node.setTopLeftCorner(topLeftCorner);
            node.setTopRightCorner(topRightCorner);
        }catch( XMustBeLargerThanZeroException e)
        {

        }catch (YMustBeLargerThanZeroException e)
        {

        }
        System.out.print(node.hashX("192.111.23.4") + ", " + node.hashY("192.111.23.4") + "\n");
        System.out.print(node.hashX("255.255.255.255") + ", " + node.hashY("255.255.255.255") + "\n");
        System.out.print(node.hashX("0.0.0.0") + ", " + node.hashY("0.0.0.0") + "\n");
        System.out.print(node.hashX("78.31.3.129") + ", " + node.hashY("78.31.3.129") + "\n");
        assertEquals(true, node.checkIfInMyZone(node.hashX("192.111.23.4"), node.hashY("192.111.23.4")));
        assertEquals(true, node.checkIfInMyZone(node.hashX("255.255.255.255"), node.hashY("255.255.255.255")));
        assertEquals(true, node.checkIfInMyZone(node.hashX("0.0.0.0"), node.hashY("0.0.0.0")));
        assertEquals(true, node.checkIfInMyZone(node.hashX("78.31.3.129"), node.hashY("78.31.3.129")));
    }
    @Test
    public void test_Node_checkIfInMyZone_False()
    {
        Node node = new Node();
        try {
            Corner bottomLeftCorner = new Corner(0.6, 0.4);
            Corner bottomRightCorner= new Corner(0.9, 0.4);
            Corner topLeftCorner    = new Corner(0.6, 0.9);
            Corner topRightCorner   = new Corner(0.9, 0.9);
            node.setBottomLeftCorner(bottomLeftCorner);
            node.setBottomRightCorner(bottomRightCorner);
            node.setTopLeftCorner(topLeftCorner);
            node.setTopRightCorner(topRightCorner);
        }catch( XMustBeLargerThanZeroException e)
        {

        }catch (YMustBeLargerThanZeroException e)
        {

        }
        System.out.print(node.hashX("192.111.23.4") + ", " + node.hashY("192.111.23.4") + "\n");
        System.out.print(node.hashX("255.255.255.255") + ", " + node.hashY("255.255.255.255") + "\n");
        System.out.print(node.hashX("0.0.0.0") + ", " + node.hashY("0.0.0.0") + "\n");
        System.out.print(node.hashX("78.31.3.129") + ", " + node.hashY("78.31.3.129") + "\n");
        assertEquals(false, node.checkIfInMyZone(node.hashX("192.111.23.4"), node.hashY("192.111.23.4")));
        assertEquals(false, node.checkIfInMyZone(node.hashX("255.255.255.255"), node.hashY("255.255.255.255")));
        assertEquals(false, node.checkIfInMyZone(node.hashX("0.0.0.0"), node.hashY("0.0.0.0")));
        assertEquals(false, node.checkIfInMyZone(node.hashX("78.31.3.129"), node.hashY("78.31.3.129")));
    }
}
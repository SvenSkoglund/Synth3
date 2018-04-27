package com.svenskoglund.synth;

import com.jsyn.JSyn;
import com.jsyn.unitgen.FilterStateVariable;
import com.jsyn.unitgen.LineOut;
import com.jsyn.unitgen.SquareOscillator;

/**
 * Play a tone using a JSyn oscillator.
 * 
 * @author Phil Burk (C) 2010 Mobileer Inc
 */
public class PlayTone {
	com.jsyn.Synthesizer synth;
	FilterStateVariable myFilter;
	SquareOscillator osc;
	LineOut lineOut;

	private void test() {

		MPU6050 mp = new MPU6050();
		// Create a context for the synthesizer.
		synth = JSyn.createSynthesizer();

		// Start synthesizer using default stereo output at 44100 Hz.
		synth.start();

		// Add a tone generator.
		synth.add(osc = new SquareOscillator());
		// Add a stereo audio output unit.
		synth.add(lineOut = new LineOut());
		synth.add(myFilter = new FilterStateVariable());
		myFilter.lowPass.connect(lineOut.input);
		// Connect the oscillator to both channels of the output.
		osc.output.connect(0, myFilter.input, 0);
		// osc.output.connect(0, myFilter.input, 1);
		myFilter.output.connect(0, lineOut.input, 0); /* Left side */
		myFilter.output.connect(0, lineOut.input, 1);
		// Set the frequency and amplitude for the sine wave.
		osc.frequency.set(345.0);
		osc.amplitude.set(0.6);

		// We only need to start the LineOut. It will pull data from the
		// oscillator.
		lineOut.start();

		System.out.println("You should now be hearing a sine wave. ---------");

		// Sleep while the sound is generated in the background.
		while (true) {
			System.out.println(mp.getAccelAccelerations()[0] +" " + mp.getAccelAccelerations()[1] +" " + mp.getAccelAccelerations()[2]);
		}
//		System.out.println("Stop playing. -------------------");
//		// Stop everything.
//		synth.stop();
	}

	public static void main(String[] args) {
		new PlayTone().test();
	}
}
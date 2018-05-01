package com.svenskoglund.synth;

import com.jsyn.JSyn;
import com.jsyn.unitgen.Add;
import com.jsyn.unitgen.FilterLowPass;
import com.jsyn.unitgen.LineOut;
import com.jsyn.unitgen.SineOscillator;
import com.jsyn.unitgen.SquareOscillator;

/**
 * Play a tone using a JSyn oscillator.
 * 
 * @author Phil Burk (C) 2010 Mobileer Inc
 */
public class PlayTone {
	com.jsyn.Synthesizer synth;
	FilterLowPass myFilter;
	SquareOscillator osc;
	LineOut lineOut;
	SineOscillator lfo;
	Double lfoAmp;
	Add freqAdder;

	private void test() {

		MPU6050 mp = new MPU6050();
		// Create a context for the synthesizer.
		synth = JSyn.createSynthesizer();

		// Start synthesizer using default stereo output at 44100 Hz.
		synth.start();

		// Add a tone generator, lfo, filter, and line out.
		synth.add(freqAdder = new Add());
		synth.add(osc = new SquareOscillator());
		synth.add(lfo = new SineOscillator());
		synth.add(myFilter = new FilterLowPass());
		synth.add(lineOut = new LineOut());
		// connect osc -> lfo
		osc.output.connect(myFilter.input);
		// freqAdder.output.connect(lfo.frequency);
		// connect lfo -> output
		myFilter.output.connect((lineOut.input));
		;
		lfo.output.connect(0, myFilter.input, 0);
		// Connect the oscillator to both channels of the output.
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
		lfo.frequency.set(5);
		double vertChange = 0;
		while (true) {
//			try {
//				Thread.sleep(50);
//			} catch (InterruptedException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}

			// Double lfoAmp = mp.readScaledGyroscopeValues()[0];
			myFilter.frequency.set(Math.abs((mp.readScaledGyroscopeValues()[0] * 50 + 500)));
			;
			// myFilter.set.set(mp.readScaledGyroscopeValues()[1] + 1);
			vertChange += (mp.getAccelAccelerations()[0]) * (mp.getAccelAccelerations()[0]) * 1000;
			osc.frequency.set(vertChange + 400);
			System.out.println(osc.frequency.get());

		}
		// System.out.println("Stop playing. -------------------");
		// // Stop everything.
		// synth.stop();
	}

	public static void main(String[] args) {
		new PlayTone().test();
	}
}